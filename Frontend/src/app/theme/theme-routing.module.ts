import { NgModule } from '@angular/core';
import { ThemeComponent } from './theme.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from "../auth/_guards/auth.guard";

import { HomeDriverComponent } from './pages/aside-left-display-disabled/home-driver/home-driver.component';
import { HomeOwnerComponent } from './pages/aside-left-display-disabled/home-owner/home-owner.component';
import { FindCarplaceComponent } from './pages/aside-left-display-disabled/find-carplace/find-carplace.component';
import { ExtensionStopsComponent } from './pages/aside-left-display-disabled/extension-stops/extension-stops.component';
import { CarComponent } from './pages/aside-left-display-disabled/car/car.component';
import { ReportDriverComponent } from './pages/aside-left-display-disabled/report-driver/report-driver.component';
import { ReportNearComponent } from './pages/aside-left-display-disabled/report-near/report-near.component';
import { PaymentComponent } from './pages/aside-left-display-disabled/payment/payment.component';
import { UsefulNumbersComponent } from './pages/aside-left-display-disabled/useful-numbers/useful-numbers.component';
import { LegislationsComponent } from './pages/aside-left-display-disabled/legislations/legislations.component';
import { AddCarComponent } from './pages/aside-left-display-disabled/addCar/addCar.component';
import { ReportHystoryComponent } from './pages/aside-left-display-disabled/report-hystory/report-hystory.component';
import { ManagementParkComponent } from './pages/aside-left-display-disabled/management-park/management-park.component';
import { ManagementSlotComponent } from './pages/aside-left-display-disabled/management-slot/management-slot.component';
import { ReportOwnerComponent } from './pages/aside-left-display-disabled/report-owner/report-owner.component';
import { SignupComponent } from './pages/aside-left-display-disabled/signup/signup.component';

const routes: Routes = [
    {
        "path": "",
        "component": ThemeComponent,
        "canActivate": [AuthGuard],
        "children": [
            {
                "path": "index",
                "loadChildren": ".\/pages\/aside-left-display-disabled\/index\/index.module#IndexModule"
            },
            {
				path: 'findCarPlace',
				loadChildren: "./pages/aside-left-display-disabled/find-carplace/find-carplace.module#FindCarplaceModule"
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
                "path": "inner",
                "loadChildren": ".\/pages\/default\/inner\/inner.module#InnerModule"
            },
            {
                "path": "profile",
                "loadChildren": ".\/pages\/default\/profile\/profile.module#ProfileModule"
            },
            {
                "path": "404",
                "loadChildren": ".\/pages\/default\/not-found\/not-found.module#NotFoundModule"
            },
            {
                "path": "",
                "redirectTo": "index",
                "pathMatch": "full"
            }
        ]
    },
    {
        "path": "**",
        "redirectTo": "404",
        "pathMatch": "full"
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ThemeRoutingModule { }