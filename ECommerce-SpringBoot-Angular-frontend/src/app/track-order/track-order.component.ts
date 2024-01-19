import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-track-order',
  templateUrl: './track-order.component.html',
  styleUrls: ['./track-order.component.scss'],
})
export class TrackOrderComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private authService: AuthService,
    private fb: FormBuilder
  ) {}

  order: any;
  searchOrderForm: FormGroup;
  orderChecked: boolean = false;

  ngOnInit(): void {
    this.searchOrderForm = this.fb.group({
      trackingId: [null, [Validators.required]],
    });
  }
  submitForm() {
    this.authService
      .getOrderByTrackingId(this.searchOrderForm.get('trackingId').value)
      .subscribe(
        (res) => {
          this.order = res;
          this.orderChecked = false;
        },
        (error) => {
          this.order = null;
          this.orderChecked = true;
        }
      );
  }
}
