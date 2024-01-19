import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-post-coupon',
  templateUrl: './post-coupon.component.html',
  styleUrls: ['./post-coupon.component.scss'],
})
export class PostCouponComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService
  ) {}

  couponForm: FormGroup;

  ngOnInit(): void {
    this.couponForm = this.fb.group({
      name: [null, [Validators.required]],
      code: [null, [Validators.required]],
      discount: [null, [Validators.required]],
      expirationDate: [null, [Validators.required]],
    });
  }

  addCoupon() {
    if (this.couponForm.valid) {
      this.adminService.addCoupon(this.couponForm.value).subscribe((res) => {
        if (res.id != null) {
          this.snackbar.open('Coupon Posted Successfully', 'Close', {
            duration: 5000,
          });
          this.router.navigateByUrl('admin/dashboard');
        } else {
          this.snackbar.open(res.message, 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar',
          });
          this.router.navigateByUrl('admin/dashboard');
        }
      });
    } else {
      this.couponForm.markAllAsTouched();
    }
  }
}
