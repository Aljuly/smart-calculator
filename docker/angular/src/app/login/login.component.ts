import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, AuthenticationService, ComunicationService } from '../_services';
import { AlertComponent } from '../_directives';

@Component({templateUrl: './login.component.html'})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    public activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private comunicationService: ComunicationService) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // reset login status
    this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || 'operation/100000';
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {
          this.comunicationService.changeLoggedInUser(data);
          this.router.navigate([this.returnUrl]);
          this.activeModal.dismiss();
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }

  onRegisterClick() {
    this.router.navigate(['/register']);
    this.activeModal.dismiss();
  }

  onDismissClick() {
    this.router.navigate([this.returnUrl]);
    this.activeModal.dismiss('Cross click');
  }
}

@Component({
  selector: 'app-login-modal-componet',
  template: '<div></div>'
})
export class LoginModalComponent implements OnInit {
  constructor(private modalService: NgbModal) {}

  ngOnInit() {
    setTimeout(() => this.modalService.open(LoginComponent));
  }
}
