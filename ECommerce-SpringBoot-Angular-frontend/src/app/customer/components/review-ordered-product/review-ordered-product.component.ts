import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/admin/services/admin.service';
import { CustomerService } from '../../services/customer.service';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-review-ordered-product',
  templateUrl: './review-ordered-product.component.html',
  styleUrls: ['./review-ordered-product.component.scss'],
})
export class ReviewOrderedProductComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute
  ) {}

  productId: string = this.activatedRoute.snapshot.params['productId'];
  reviewForm: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  onFileSelectd(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }
  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.reviewForm = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }

  submitForm() {
    if (this.reviewForm.valid) {
      const formData: FormData = new FormData();
      formData.append('img', this.selectedFile);
      formData.append('productId', this.productId);
      formData.append('userId', UserStorageService.getUserId().toString());
      formData.append('rating', this.reviewForm.get('rating').value);
      formData.append('description', this.reviewForm.get('description').value);
      if (!this.selectedFile) {
        this.snackbar.open('Select one image', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar',
        });
        return;
      }

      this.customerService.giveReview(formData).subscribe((res) => {
        if (res.id != null) {
          this.snackbar.open('Give Review Successfully', 'Close', {
            duration: 5000,
          });
          this.router.navigateByUrl('customer/orders');
        } else {
          this.snackbar.open('Give Review Failed', 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar',
          });
        }
      });
    } else {
      for (const i in this.reviewForm.controls) {
        this.reviewForm.controls[i].markAsDirty();
        this.reviewForm.controls[i].updateValueAndValidity();
      }
    }
  }
}
