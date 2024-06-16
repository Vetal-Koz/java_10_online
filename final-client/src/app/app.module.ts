import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlpComponent } from './pages/plp/plp.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { PdpComponent } from './pages/pdp/pdp.component';
import { HeaderComponent } from './layout/header/header.component';
import { SearchComponent } from './layout/search/search.component';


@NgModule({
  declarations: [
    AppComponent,
    PlpComponent,
    PdpComponent,
    HeaderComponent,
    SearchComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
