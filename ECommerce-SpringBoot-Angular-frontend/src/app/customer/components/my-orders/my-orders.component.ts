import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/admin/services/admin.service';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss'],
})
export class MyOrdersComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private customerService: CustomerService
  ) {}

  myOrders: any;

  ngOnInit(): void {
    this.getMyOrders();
  }

  getMyOrders() {
    this.customerService.getOrdersByUserId().subscribe((res) => {
      this.myOrders = res;
    });
  }
  // changeOrderStatus(orderId: number, status: string) {
  //   this.adminService.changeOrderSatus(orderId, status).subscribe((res) => {
  //     if (res.id != null) {
  //       this.snackbar.open('Change Order Status Successfully', 'Close', {
  //         duration: 5000,
  //       });
  //       this.getPlacedOrders();
  //     } else {
  //       this.snackbar.open('Something went Wrong!', 'Close', {
  //         duration: 5000,
  //         panelClass: 'error-snackbar',
  //       });
  //       this.router.navigateByUrl('admin/dashboard');
  //     }
  //   });
  // }
}
