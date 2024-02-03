import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.scss'],
})
export class PlaceOrderComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {}

  orderForm!: FormGroup;

  ngOnInit(): void {
    this.orderForm = this.fb.group({
      address: [null, [Validators.required]],
      orderDescription: [null],
    });
  }
  placeOrder() {
    this.customerService.placeOrder(this.orderForm.value).subscribe(
      (res) => {
        if (res.id != null) {
          this.snackbar.open('Order placed Successfully', 'Close', {
            duration: 5000,
          });
          this.router.navigateByUrl('/customer/orders');
          this.dialog.closeAll();
        }
      },
      (error) => {
        this.snackbar.open(error.error, 'Close', {
          duration: 5000,
        });
      }
    );
  }

  closeForm() {
    this.dialog.closeAll();
  }
}
