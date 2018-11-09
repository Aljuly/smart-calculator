import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { routes } from './app.routes';
import { APP_BASE_HREF } from '@angular/common';

import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { PageComponent } from './page/page.component';
import { HeaderComponent } from './page/header/header.component';
import { FooterComponent } from './page/footer/footer.component';
import { SectionComponent } from './page/section/section.component';
import { DefboardComponent } from './page/section/defboard/defboard.component';
import { InputBoardComponent } from './page/section/inputboard/inputboard.component';
import { ResultComponent } from './page/section/result/result.component';
import { DataService } from './_services/data.service';
import { ComunicationService } from './_services';
import { AlertService, AuthenticationService, UserService } from './_services';
// import { MockBackendInterceptor } from './_helpers';
import { AlertComponent } from './_directives';
import { AuthGuard } from './_guards';
import { JwtInterceptor, ErrorInterceptor, Formatter } from './_helpers';
import { LoginComponent, LoginModalComponent } from './login';
import { RegisterComponent, RegisterModalComponent } from './register';
import { DivisionFormatterComponent } from './page/section/result/division-formatter';
import { MultiplycationFormatterComponent } from './page/section/result/multiplycation-formatter';
import { AdditionComponent } from './page/section/result/addition-component';
import { SignComponent } from './page/header/sign';
import { SubtractionFormatterComponent } from './page/section/result/subtraction-formatter';
import { CommonResultComponent } from './page/section/inputboard/common-result/common-result.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    NgbModule.forRoot(),
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    SidebarComponent,
    PageComponent,
    HeaderComponent,
    FooterComponent,
    SectionComponent,
    DefboardComponent,
    InputBoardComponent,
    ResultComponent,
    LoginComponent,
    LoginModalComponent,
    RegisterComponent,
    RegisterModalComponent,
    AlertComponent,
    DivisionFormatterComponent,
    MultiplycationFormatterComponent,
    AdditionComponent,
    SignComponent,
    SubtractionFormatterComponent,
    CommonResultComponent
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
   //  { provide: HTTP_INTERCEPTORS, useClass: MockBackendInterceptor, multi: true },
    { provide: APP_BASE_HREF, useValue: '/' },
    DataService,
    ComunicationService,
    Formatter
  ],
  entryComponents: [LoginComponent, RegisterComponent],
  bootstrap: [AppComponent],
  exports: [SubtractionFormatterComponent]
})
export class AppModule {}
