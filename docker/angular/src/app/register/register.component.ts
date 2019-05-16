import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { AlertService, UserService } from '../_services';
import { AlertComponent } from '../_directives';

@Component({templateUrl: './register.component.html'})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    public activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userName: ['', Validators.required],
      login: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Registration successful', true);
          this.router.navigate(['/login']);
          this.activeModal.dismiss();
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

  onLoginClick() {
    this.router.navigate(['/login']);
    this.activeModal.dismiss();
  }
}

@Component({
  selector: 'app-register-modal-componet',
  template: `<div></div>`
})
export class RegisterModalComponent implements OnInit {
  constructor(private modalService: NgbModal) {}

  ngOnInit() {
    setTimeout(() => this.modalService.open(RegisterComponent));
  }
}
