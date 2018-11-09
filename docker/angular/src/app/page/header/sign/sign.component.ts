import { Component, OnInit } from '@angular/core';
import { User } from '../../../_models';
import { ComunicationService, AuthenticationService } from '../../../_services';

@Component({
    selector: 'app-sign',
    templateUrl: './sign.component.html'
  })
export class SignComponent implements OnInit {
    user: User;
    signedIn: boolean;
    constructor(
      private comunicationService: ComunicationService,
      private authenticationService: AuthenticationService) { }
    ngOnInit(): void {
      // Be ready to optain actual data
      this.comunicationService.userMessage.subscribe(user => {
        this.user = user;
        this.signedIn = !user.isEmpty();
      });
    }
    logout() {
      // remove authenticated user
      this.authenticationService.logout();
      // setUp empty user
      this.comunicationService.changeLoggedInUser(new User());
    }
}
