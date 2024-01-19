import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss'],
})
export class UpdateProductComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute
  ) {}

  productId = this.activatedRoute.snapshot.params['productId'];
  productForm: FormGroup;
  selectedFile: File | null;
  listCategories: any = [];
  imagePreview: string | ArrayBuffer | null;
  oldImgae: string | ArrayBuffer | null;
  selectedChanged: boolean = false;
  onFileSelectd(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.selectedChanged = true;
    this.oldImgae = null;
  }
  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.productForm = this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
    this.getAllCategories();
    this.getProductById();
  }

  getAllCategories() {
    this.adminService.getAllCategories().subscribe((res) => {
      this.listCategories = res;
    });
  }

  getProductById() {
    this.adminService.getProductById(this.productId).subscribe((res) => {
      this.productForm.patchValue(res);
      this.oldImgae = 'data:image/jpeg;base64, ' + res.byteImg;
    });
  }

  updateProduct() {
    if (this.productForm.valid) {
      const formData: FormData = new FormData();
      if (this.selectedChanged && this.selectedFile) {
        formData.append('img', this.selectedFile);
      }

      formData.append('categoryId', this.productForm.get('categoryId').value);
      formData.append('name', this.productForm.get('name').value);
      formData.append('description', this.productForm.get('description').value);
      formData.append('price', this.productForm.get('price').value);

      this.adminService
        .updateProduct(this.productId, formData)
        .subscribe((res) => {
          if (res.id != null) {
            this.snackbar.open('Product Updated Successfully', 'Close', {
              duration: 5000,
            });
            this.router.navigateByUrl('admin/dashboard');
          } else {
            this.snackbar.open('Product Updated Failed', 'Close', {
              duration: 5000,
              panelClass: 'error-snackbar',
            });
            this.router.navigateByUrl('admin/dashboard');
          }
        });
    } else {
      for (const i in this.productForm.controls) {
        this.productForm.controls[i].markAsDirty();
        this.productForm.controls[i].updateValueAndValidity();
      }
    }
  }
}
