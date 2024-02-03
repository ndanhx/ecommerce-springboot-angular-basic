import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss'],
})
export class AccountComponent implements OnInit {
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private adminService: AdminService,
    private fb: FormBuilder
  ) {}

  accounts: any;

  ngOnInit(): void {
    this.getAllAccounts();
  }

  getAllAccounts() {
    this.adminService.getAllAccounts().subscribe((res) => {
      this.accounts = res;
    });
  }

  submitForm() {}
}
