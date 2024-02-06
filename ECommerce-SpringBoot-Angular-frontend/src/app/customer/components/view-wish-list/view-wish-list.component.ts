import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-view-wish-list',
  templateUrl: './view-wish-list.component.html',
  styleUrls: ['./view-wish-list.component.scss'],
})
export class ViewWishListComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute
  ) {}

  products: any[] = [];

  ngOnInit(): void {
    this.getAllWishListByUserId();
  }

  getAllWishListByUserId() {
    this.customerService.getAllWishListByUserId().subscribe((res) => {
      res.forEach((element) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
      });
      this.products = res;
    });
  }

  deleteWishList(wishListId: any) {
    this.customerService.deleteWishList(wishListId).subscribe(
      (res) => {
        this.snackbar.open('Removed Product Successfully ', 'Close', {
          duration: 5000,
        });
        this.getAllWishListByUserId();
      },
      (error) => {
        this.snackbar.open(error.error, 'Close', {
          duration: 5000,
        });
      }
    );
  }

  addToCart(productId: any) {
    this.customerService.addToCart(productId).subscribe(
      (res) => {
        this.snackbar.open('Product added to cart Successfully', 'Close', {
          duration: 5000,
        });
      },
      (error) => {
        this.snackbar.open('Product added to cart Failed', 'Close', {
          duration: 5000,
        });
      }
    );
  }
}
