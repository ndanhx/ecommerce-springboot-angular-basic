import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/admin/services/admin.service';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss'],
})
export class EditCategoryComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public categoryId: any
  ) {}
  categoryForm!: FormGroup;
  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
    this.getCategoryById();
  }

  getCategoryById() {
    this.adminService
      .getCategoryById(this.categoryId.categoryId)
      .subscribe((res) => {
        this.categoryForm.patchValue(res);
      });
    console.log(
      'categoryid: ',
      this.categoryId.categoryId,
      'form',
      this.categoryForm.value
    );
  }

  updateCategory() {
    if (this.categoryForm.valid) {
      const formData: FormData = new FormData();
      formData.append('name', this.categoryForm.get('name').value);
      formData.append(
        'description',
        this.categoryForm.get('description').value
      );
      this.adminService
        .updateCategory(this.categoryId.categoryId, formData)
        .subscribe((res) => {
          if (res.id != null) {
            this.snackbar.open('Updated Category Successfully', 'Close', {
              duration: 5000,
            });
          } else {
            this.snackbar.open('  Updated  Category Failed', 'Close', {
              duration: 5000,
              panelClass: 'error-snackbar',
            });
          }
          this.dialog.closeAll();
          window.location.reload();
        });
    } else {
      for (const i in this.categoryForm.controls) {
        this.categoryForm.controls[i].markAsDirty();
        this.categoryForm.controls[i].updateValueAndValidity();
      }
    }
  }

  closeForm() {
    this.dialog.closeAll();
  }
}
