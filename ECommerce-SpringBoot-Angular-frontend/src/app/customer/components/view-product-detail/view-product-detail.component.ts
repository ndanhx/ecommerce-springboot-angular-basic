import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-view-product-detail',
  templateUrl: './view-product-detail.component.html',
  styleUrls: ['./view-product-detail.component.scss'],
})
export class ViewProductDetailComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.getProductDetailById();
  }

  productId: any = this.activatedRoute.snapshot.params['productId'];
  product: any;
  FAQs: any[] = [];
  reviews: any[] = [];
  getProductDetailById() {
    this.customerService
      .getProductDetailById(this.productId)
      .subscribe((res) => {
        this.product = res.productDto;
        this.product.processedImg =
          'data:image/jpeg;base64,' + res.productDto.byteImg;
        this.FAQs = res.faqDtoList;

        res.reviewDtoList.forEach((element) => {
          element.processedImg =
            'data:image/jpeg;base64,' + element.returnedImg;
          this.reviews.push(element);
        });
      });
  }

  getStars(rating: number): number[] {
    return new Array(rating);
  }

  addProductToWishList() {
    const wishListDto = {
      productId: this.productId,
      userId: UserStorageService.getUserId(),
    };
    this.customerService.addProductToWishList(wishListDto).subscribe((res) => {
      if (res.id != null) {
        this.snackbar.open('Added Wish List Successfully', 'Close', {
          duration: 5000,
        });
        this.router.navigateByUrl('customer/orders');
      } else {
        this.snackbar.open('Added Wish List Failed', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar',
        });
      }
    });
  }
}
