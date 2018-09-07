import { LayoutModule } from '../layout/layout.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagesRoutingModule } from './pages-routing.module';
import { PagesComponent } from './pages.component';
import { PartialsModule } from '../partials/partials.module';
import { ActionComponent } from './header/action/action.component';
import { ProfileComponent } from './header/profile/profile.component';
import { CoreModule } from '../../core/core.module';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ErrorPageComponent } from './snippets/error-page/error-page.component';
import { InnerComponent } from './components/inner/inner.component';
import { HomeDriverComponent } from './components/home-driver/home-driver.component';
import { HomeOwnerComponent } from './components/home-owner/home-owner.component';
import { AddCarComponent } from './components/addCar/addCar.component';
import { CarComponent } from './components/car/car.component';
import { ExtensionStopsComponent } from './components/extension-stops/extension-stops.component';
import { ExtensionStopsRowComponent } from './components/extension-stops-row/extension-stops-row.component';
import { FindCarplaceComponent } from './components/find-carplace/find-carplace.component';
import { IntestazioneComponent } from './components/intestazione/intestazione.component';
import { LegislationsComponent } from './components/legislations/legislations.component';
import { LoginComponent } from './components/login/login.component';
import { ManagementParkComponent } from './components/management-park/management-park.component';
import { ManagementSlotComponent } from './components/management-slot/management-slot.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ReportDriverComponent } from './components/report-driver/report-driver.component';
import { ReportHystoryComponent } from './components/report-hystory/report-hystory.component';
import { ReportNearComponent } from './components/report-near/report-near.component';
import { ReportOwnerComponent } from './components/report-owner/report-owner.component';
import { SignupComponent } from './components/signup/signup.component';
import { UsefulNumbersComponent } from './components/useful-numbers/useful-numbers.component';
import { AgmCoreModule } from '../../../../node_modules/@agm/core';

@NgModule({
	declarations: [
		PagesComponent,
		ActionComponent,
		ProfileComponent,
		ErrorPageComponent,
		InnerComponent,
		AddCarComponent,
		CarComponent,
		ExtensionStopsComponent,
		ExtensionStopsRowComponent,
		FindCarplaceComponent, //uses google maps
		HomeDriverComponent,
		HomeOwnerComponent,
		IntestazioneComponent,
		LegislationsComponent,
		LoginComponent,
		ManagementParkComponent, //uses google maps
		ManagementSlotComponent,
		PaymentComponent,
		ReportDriverComponent,
		ReportHystoryComponent,
		ReportNearComponent,
		ReportOwnerComponent,
		SignupComponent,
		UsefulNumbersComponent
	],
	imports: [
		CommonModule,
		HttpClientModule,
		FormsModule,
		PagesRoutingModule,
		CoreModule,
		LayoutModule,
		PartialsModule,
		AngularEditorModule,
		AgmCoreModule
	],
	providers: []
})
export class PagesModule {
}
