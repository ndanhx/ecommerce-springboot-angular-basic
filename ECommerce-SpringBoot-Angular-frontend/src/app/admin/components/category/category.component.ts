import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { MatDialog } from '@angular/material/dialog';
import { PostCategoryComponent } from './post-category/post-category.component';
import { EditCategoryComponent } from './edit-category/edit-category.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss'],
})
export class CategoryComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {}

  categories: any;

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories() {
    this.adminService.getAllCategories().subscribe((res) => {
      this.categories = res;
    });
  }

  submitForm() {}

  postCategory() {
    this.dialog.open(PostCategoryComponent);
  }

  editCategory(categoryId: any) {
    const dialogRef = this.dialog.open(EditCategoryComponent, {
      data: { categoryId: categoryId },
    });
  }

  deleteCategory(categoryId: any) {
    this.adminService.deleteCategory(categoryId).subscribe((res) => {
      this.snackbar.open('Deleted Category Successfully', 'Close', {
        duration: 5000,
      });
      this.getAllCategories();
    });
  }
}
