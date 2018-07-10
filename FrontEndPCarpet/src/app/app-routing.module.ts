import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import {RouterModule, Routes} from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { HomeDriverComponent } from '../components/home-driver/home-driver.component';
import { HomeOwnerComponent } from '../components/home-owner/home-owner.component';


const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'homeDriver', component: HomeDriverComponent},
  {path: 'homeOwner', component: HomeOwnerComponent}
  
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  declarations: []
})
export class AppRoutingModule { }
