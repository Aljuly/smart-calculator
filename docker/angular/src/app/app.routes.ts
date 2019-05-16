import { Routes } from '@angular/router';

import { SectionComponent } from './page/section/section.component';
import { LoginModalComponent } from './login';
import { RegisterModalComponent } from './register';
import { AuthGuard } from './_guards';

export const routes: Routes = [
  { path: 'operation/:id', component: SectionComponent },
  { path: '', component: SectionComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginModalComponent },
  { path: 'register', component: RegisterModalComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];
