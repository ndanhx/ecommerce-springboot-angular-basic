import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { PlaceOrderComponent } from '../place-order/place-order.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {}

  cartItems: any[] = [];
  order: any;
  couponForm!: FormGroup;

  getCart() {
    this.cartItems = [];
    this.customerService.getCartByUserId().subscribe((res) => {
      this.order = res;
      console.log(res);
      res.cartItems.forEach((element) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.cartItems.push(element);
      });
    });
  }

  ngOnInit(): void {
    this.getCart();
    this.couponForm = this.fb.group({
      code: [null, [Validators.required]],
    });
  }
  applyCoupon() {
    this.customerService
      .applyCoupon(this.couponForm.get(['code'])!.value)
      .subscribe(
        (res) => {
          this.snackbar.open('Coupon Applied Successfully', 'Close', {
            duration: 5000,
          });
          this.getCart();
        },
        (error) => {
          this.snackbar.open(error.error, 'Close', {
            duration: 5000,
          });
        }
      );
  }
  increaseProductQuantity(productId: any) {
    this.customerService.increaseProductQuantity(productId).subscribe(
      (res) => {
        this.snackbar.open('Product Quantity increased ', 'Close', {
          duration: 5000,
        });
        this.getCart();
      },
      (error) => {
        this.snackbar.open(error.error, 'Close', {
          duration: 5000,
        });
      }
    );
  }
  decreaseProductQuantity(productId: any) {
    this.customerService.decreaseProductQuantity(productId).subscribe(
      (res) => {
        this.snackbar.open('Product Quantity decreased ', 'Close', {
          duration: 5000,
        });
        this.getCart();
      },
      (error) => {
        this.snackbar.open(error.error, 'Close', {
          duration: 5000,
        });
      }
    );
  }

  placeOrder() {
    this.dialog.open(PlaceOrderComponent);
  }
}
