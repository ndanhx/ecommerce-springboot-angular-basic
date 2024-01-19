import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    private fb: FormBuilder
  ) {}

  products: any[] = [];
  searchProductForm: FormGroup;

  ngOnInit(): void {
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title: [null, [Validators.required]],
    });
  }

  getAllProducts() {
    this.adminService.getAllProducts().subscribe((res) => {
      res.forEach((element) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        this.products.push(element);
      });
    });
  }

  submitForm() {
    this.products = [];
    const title = this.searchProductForm.get('title')!.value;
    this.adminService.getAllProductsByName(title).subscribe((res) => {
      res.forEach((element) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteImg;
        this.products.push(element);
      });
    });
  }

  deleteProduct(productId: any) {
    alert(`Don't use this feature !!!`);

    // this.adminService.deleteProduct(productId).subscribe((res) => {
    //   if (res == null) {
    //     this.snackbar.open('Product Deleted Successfully', 'Close', {
    //       duration: 5000,
    //     });
    //     this.products = [];
    //     this.getAllProducts();
    //   } else {
    //     this.snackbar.open('Product not found', 'Close', {
    //       duration: 5000,
    //       panelClass: 'error-snackbar',
    //     });
    //   }
    // });
  }
}
