import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PagesComponent } from './pages.component';
import { ActionComponent } from './header/action/action.component';
import { NgxPermissionsGuard } from 'ngx-permissions';
import { ProfileComponent } from './header/profile/profile.component';
import { ErrorPageComponent } from './snippets/error-page/error-page.component';
import { InnerComponent } from "./components/inner/inner.component";
import { HomeDriverComponent } from './components/home-driver/home-driver.component';
import { HomeOwnerComponent } from './components/home-owner/home-owner.component';
import { FindCarplaceComponent } from './components/find-carplace/find-carplace.component';
import { ExtensionStopsComponent } from './components/extension-stops/extension-stops.component';
import { CarComponent } from './components/car/car.component';
import { ReportDriverComponent } from './components/report-driver/report-driver.component';
import { ReportNearComponent } from './components/report-near/report-near.component';
import { PaymentComponent } from './components/payment/payment.component';
import { UsefulNumbersComponent } from './components/useful-numbers/useful-numbers.component';
import { LegislationsComponent } from './components/legislations/legislations.component';
import { LoginComponent } from './components/login/login.component';
import { AddCarComponent } from './components/addCar/addCar.component';
import { ReportHystoryComponent } from './components/report-hystory/report-hystory.component';
import { ManagementParkComponent } from './components/management-park/management-park.component';
import { ManagementSlotComponent } from './components/management-slot/management-slot.component';
import { ReportOwnerComponent } from './components/report-owner/report-owner.component';
import { SignupComponent } from './components/signup/signup.component';
//import { LoginComponent } from './auth/login/login.component';

/* const routes: Routes = [
	{
		path: '',
		component: PagesComponent,
		// Remove comment to enable login
		// canActivate: [NgxPermissionsGuard],
		data: {
			permissions: {
				only: ['ADMIN', 'USER'],
				except: ['GUEST'],
				redirectTo: '/login'
			}
		},
		children: [
			{
				path: '',
				loadChildren: './components/dashboard/dashboard.module#DashboardModule'
			},
			{
				path: 'builder',
				loadChildren: './builder/builder.module#BuilderModule'
			},
			{
				path: 'header/actions',
				component: ActionComponent
			},
			{
				path: 'profile',
				component: ProfileComponent
			},
			{
				path: 'inner',
				component: InnerComponent
			},
		]
	},
	{
		path: 'login',
		// canActivate: [NgxPermissionsGuard],
		loadChildren: './auth/auth.module#AuthModule',
		data: {
			permissions: {
				except: 'ADMIN'
			}
		},
	},
	{
		path: '404',
		component: ErrorPageComponent
	},
	{
		path: 'error/:type',
		component: ErrorPageComponent
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
}) */

const routes: Routes = [
	{
		path: '',
		component: PagesComponent,
		//redirectTo: '/login',
		// Remove comment to enable login
		//canActivate: [NgxPermissionsGuard],
		
		// data: {
		// 	permissions: {
		// 		only: ['ADMIN', 'USER'],
		// 		except: ['GUEST'],
		// 		redirectTo: '/login'
		// 	}
		//  },
		children: [
			 {
				path: '',
				component: LoginComponent
				//loadChildren: './components/dashboard/dashboard.module#DashboardModule'
			 },
			//driver
			{
				path: 'findCarPlace',
				component: FindCarplaceComponent
			},
			{
				path: 'extensionStops',
				component: ExtensionStopsComponent
			},
			{
				path: 'car',
				component: CarComponent
			},
			{
				path: 'reportDriver',
				component: ReportDriverComponent
			},
			{
				path: 'reportNear',
				component: ReportNearComponent
			},
			{
				path: 'paymentsHystory',
				component: PaymentComponent
			},
			{
				path: 'inner',
				component: InnerComponent
			},
			{
				path: 'usefulNumbers',
				component: UsefulNumbersComponent
			},
			{
				path: 'legislations',
				component: LegislationsComponent
			},
			{
				path: 'addCar',
				component: AddCarComponent
			},
			{
				path: 'reportHystory',
				component: ReportHystoryComponent
			},
			//owner
			{
				path: 'managementPark',
				component: ManagementParkComponent
			},
			{
				path: 'managementSlot',
				component: ManagementSlotComponent
			},
			{
				path: 'reportOwner',
				component: ReportOwnerComponent
			},
			{
				path: 'reportNearOwner',
				component: ReportNearComponent
			},

			{
				path: 'signup',
				component: SignupComponent
			},
			{
				path: 'builder',
				loadChildren: './builder/builder.module#BuilderModule'
			},
		]
	},
	{
		path: 'login',		
		component: LoginComponent,
	},
	// {
	// 	path: 'login',
	// 	// canActivate: [NgxPermissionsGuard],
	// 	loadChildren: './auth/auth.module#AuthModule',
	// 	data: {
	// 		permissions: {
	// 			except: 'ADMIN'
	// 		}
	// 	},
	// },
	{
		path: '404',
		component: ErrorPageComponent
	},
	{
		path: 'error/:type',
		component: ErrorPageComponent
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PagesRoutingModule {
}
