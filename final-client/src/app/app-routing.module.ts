import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PlpComponent } from './pages/plp/plp.component';
import { PdpComponent } from './pages/pdp/pdp.component';


const routes: Routes = [
  {path: '', redirectTo: '/cars', pathMatch:'full'},
  {path: 'cars', component: PlpComponent},
  {path: 'cars/:id', component: PdpComponent}
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
