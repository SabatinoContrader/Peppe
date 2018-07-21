import { IntestazioneComponent } from '../components/intestazione/intestazione.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import {RouterModule, Routes} from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { HomeDriverComponent } from '../components/home-driver/home-driver.component';
import { HomeOwnerComponent } from '../components/home-owner/home-owner.component';
import { SignupComponent } from '../components/signup/signup.component';
import { ReportDriverComponent } from '../components/report-driver/report-driver.component';
import { CarComponent } from '../components/car/car.component';
import { AddCarComponent } from '../components/addCar/addCar.component';
import { ReportHystoryComponent } from '../components/report-hystory/report-hystory.component';
import { ReportOwnerComponent } from '../components/report-owner/report-owner.component';
import { ReportNearComponent } from '../components/report-near/report-near.component';
import { PaymentComponent } from '../components/payment/payment.component';
import { ExtensionStopsComponent } from '../components/extension-stops/extension-stops.component';
import { FindCarplaceComponent } from '../components/find-carplace/find-carplace.component';
import { ManagementParkComponent } from '../components/management-park/management-park.component';
import { UsefulNumbersComponent } from '../components/useful-numbers/useful-numbers.component';
import { LegislationsComponent } from '../components/legislations/legislations.component';
import { ManagementSlotComponent } from '../components/management-slot/management-slot.component';


const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'homeDriver', component: HomeDriverComponent},
  {path: 'homeOwner', component: HomeOwnerComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'car', component: CarComponent},
  {path: 'addCar', component: AddCarComponent},
  {path: 'reportDriver', component: ReportDriverComponent},
  {path: 'reportHystory', component: ReportHystoryComponent},
  {path: 'reportOwner', component: ReportOwnerComponent},
  {path: 'reportNear', component: ReportNearComponent},
  {path: 'findCarPlace', component: FindCarplaceComponent},
  {path: 'extensionStops', component: ExtensionStopsComponent},
  {path: 'reportDriver', component: ReportDriverComponent},
  {path: 'paymentsHystory', component: PaymentComponent},
  {path: 'managementPark', component: ManagementParkComponent},
  {path: 'usefulNumbers', component: UsefulNumbersComponent},
  {path: 'legislations', component: LegislationsComponent},
  {path: 'intestazione', component: IntestazioneComponent},
  {path: 'managementSlot', component: ManagementSlotComponent}
  
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes, {useHash: true, onSameUrlNavigation: 'reload'})],
  declarations: []
})
export class AppRoutingModule { }
