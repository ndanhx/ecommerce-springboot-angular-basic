import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.scss'],
})
export class CouponsComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    private fb: FormBuilder
  ) {}

  coupons: any;

  ngOnInit(): void {
    this.getAllCoupons();
  }

  getAllCoupons() {
    this.adminService.getAllCoupons().subscribe((res) => {
      this.coupons = res;
    });
  }

  submitForm() {}
}
