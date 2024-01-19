import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService
  ) {}

  orders: any;

  ngOnInit(): void {
    this.getPlacedOrders();
  }

  getPlacedOrders() {
    this.adminService.getAllPlacedOrders().subscribe((res) => {
      this.orders = res;
    });
  }
  changeOrderStatus(orderId: number, status: string) {
    this.adminService.changeOrderSatus(orderId, status).subscribe((res) => {
      if (res.id != null) {
        this.snackbar.open('Change Order Status Successfully', 'Close', {
          duration: 5000,
        });
        this.getPlacedOrders();
      } else {
        this.snackbar.open('Something went Wrong!', 'Close', {
          duration: 5000,
          panelClass: 'error-snackbar',
        });
        this.router.navigateByUrl('admin/dashboard');
      }
    });
  }
}
