import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/admin/services/admin.service';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-view-ordered-products',
  templateUrl: './view-ordered-products.component.html',
  styleUrls: ['./view-ordered-products.component.scss'],
})
export class ViewOrderedProductsComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.getOrderedProductsDetailsByOrderId();
  }

  orderId: any = this.activatedRoute.snapshot.params['orderId'];
  orderedProductsDetailsList = [];
  totalAmount: any;

  getOrderedProductsDetailsByOrderId() {
    this.customerService.getOrderedProducts(this.orderId).subscribe((res) => {
      res.productDtoList.forEach((element) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        this.orderedProductsDetailsList.push(element);
      });
      this.totalAmount = res.orderAmount;
    });
  }
}
