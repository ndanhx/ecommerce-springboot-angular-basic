import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../../services/admin.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-post-category',
  templateUrl: './post-category.component.html',
  styleUrls: ['./post-category.component.scss'],
})
export class PostCategoryComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    public dialog: MatDialog
  ) {}
  categoryForm!: FormGroup;
  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }

  addCategory() {
    if (this.categoryForm.valid) {
      this.adminService
        .addCategory(this.categoryForm.value)
        .subscribe((res) => {
          if (res.id != null) {
            this.snackbar.open('Category Posted Successfully', 'Close', {
              duration: 5000,
            });
            this.router.navigateByUrl('admin/category');
            this.dialog.closeAll();
          } else {
            this.snackbar.open('Category Post Failed', 'Close', {
              duration: 5000,
              panelClass: 'error-snackbar',
            });
          }
        });
    } else {
      this.categoryForm.markAllAsTouched();
    }
  }

  closeForm() {
    this.dialog.closeAll();
  }
}
