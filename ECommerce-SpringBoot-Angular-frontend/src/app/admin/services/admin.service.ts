import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environmentLocal } from 'src/app/environments/environment';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

// const BASIC_URL = 'http://localhost:8080/';
const BASIC_URL = environmentLocal.apiUrl;

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization',
      'Bearer ' + UserStorageService.getToken()
    );
  }

  //Category

  addCategory(categoryDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/category', categoryDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllCategories(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/categories', {
      headers: this.createAuthorizationHeader(),
    });
  }

  deleteCategory(categoryId: any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/admin/category/${categoryId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateCategory(categoryId: number, categoryDto: any): Observable<any> {
    return this.http.put(
      BASIC_URL + `api/admin/update-category/${categoryId}`,
      categoryDto,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  getCategoryById(categoryId: number): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/category/${categoryId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }
  //Product
  addProduct(productDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/product', productDto, {
      headers: this.createAuthorizationHeader(),
    });
  }
  getAllProducts(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/products', {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllProductsByName(name: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/products/search/${name}`, {
      headers: this.createAuthorizationHeader(),
    });
  }
  getAllProductsByCategoryId(categoryId: any): Observable<any> {
    return this.http.get(
      BASIC_URL + `api/admin/products/category/${categoryId}`,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  getProductById(productId: number): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/product/${productId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  deleteProduct(productId: any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/admin/product/${productId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateProduct(productId: number, productDto: any): Observable<any> {
    return this.http.put(
      BASIC_URL + `api/admin/update-product/${productId}`,
      productDto,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  //Coupon
  addCoupon(coupon: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/coupons', coupon, {
      headers: this.createAuthorizationHeader(),
    });
  }
  getAllCoupons(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/coupons', {
      headers: this.createAuthorizationHeader(),
    });
  }
  //Order
  getAllPlacedOrders(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/placed-orders', {
      headers: this.createAuthorizationHeader(),
    });
  }
  changeOrderSatus(orderId: number, status: string): Observable<any> {
    return this.http.get(
      BASIC_URL + `api/admin/change-order-status/${orderId}/${status}`,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  //FAQ
  postFAQ(productId: number, faqDto: any): Observable<any> {
    return this.http.post(BASIC_URL + `api/admin/faq/${productId}`, faqDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  //Analytics
  getAnalytics(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/order/analytics', {
      headers: this.createAuthorizationHeader(),
    });
  }

  //Account
  getAllAccounts(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/accounts', {
      headers: this.createAuthorizationHeader(),
    });
  }
}
