import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss'],
})
export class AnalyticsComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    private fb: FormBuilder
  ) {}

  data: any;

  ngOnInit(): void {
    this.getAnalytics();
  }

  getAnalytics() {
    this.adminService.getAnalytics().subscribe((res) => {
      this.data = res;
      console.log(res);
    });
  }
}
