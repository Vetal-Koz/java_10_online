import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlpComponent } from './pages/plp/plp.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { PdpComponent } from './pages/pdp/pdp.component';
import { HeaderComponent } from './layout/header/header.component';
import { SearchComponent } from './layout/search/search.component';
import { LoginComponent } from './pages/login/login.component';
import {AuthInterceptor} from "./config/auth-interceptor";
import { WishListComponent } from './pages/wish-list/wish-list.component';
import { RegisterComponent } from './pages/register/register.component';
import { ThanksDialogComponent } from './dialog/thanks-dialog/thanks-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    PlpComponent,
    PdpComponent,
    HeaderComponent,
    SearchComponent,
    LoginComponent,
    WishListComponent,
    RegisterComponent,
    ThanksDialogComponent
  ],
  imports: [
    BrowserModule,
    NgFor,
    NgIf,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule
  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  },],
  bootstrap: [AppComponent]
})
export class AppModule { }
