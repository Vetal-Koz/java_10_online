import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlpComponent } from './pages/plp/plp.component';
import { PdpComponent } from './pages/pdp/pdp.component';
import {LoginComponent} from "./pages/login/login.component";
import {WishListComponent} from "./pages/wish-list/wish-list.component";


const routes: Routes = [
  {path: '', redirectTo: '/cars', pathMatch:'full'},
  {path: 'cars', component: PlpComponent},
  {path: 'cars/:id', component: PdpComponent},
  {path: 'login', component: LoginComponent},
  {path: 'wishList', component: WishListComponent}
];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
