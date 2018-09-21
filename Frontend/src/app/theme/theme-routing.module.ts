import { NgModule } from '@angular/core';
import { ThemeComponent } from './theme.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from "../auth/_guards/auth.guard";

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
				loadChildren: "./pages/aside-left-display-disabled/extension-stops/extension-stops.module#ExtensionStopsModule"
			},
			{
				path: 'car',
				loadChildren: "./pages/aside-left-display-disabled/car/car.module#CarModule"
			},
			{
				path: 'reportDriver',
				loadChildren: "./pages/aside-left-display-disabled/report-driver/report-driver.module#ReportDriverModule"
			},
			{
				path: 'reportNear',
				loadChildren: "./pages/aside-left-display-disabled/report-near/report-near.module#ReportNearModule"
			},
			{
				path: 'paymentsHystory',
				loadChildren: "./pages/aside-left-display-disabled/payment/payment.module#PaymentModule"
			},
			{
				path: 'usefulNumbers',
				loadChildren: "./pages/aside-left-display-disabled/useful-numbers/useful-numbers.module#UsefulNumbersModule"
			},
			{
				path: 'legislations',
				loadChildren: "./pages/aside-left-display-disabled/legislations/legislations.module#LegislationsModule"
			},
			{
				path: 'addCar',
				loadChildren: "./pages/aside-left-display-disabled/addCar/addCar.module#AddCarModule"
			},
			{
				path: 'reportHystory',
				loadChildren: "./pages/aside-left-display-disabled/report-hystory/report-hystory.module#ReportHystoryModule"
			},
			//owner
			{
				path: 'managementPark',
				loadChildren: "./pages/aside-left-display-disabled/management-park/management-park.module#ManagementParkModule"
			},
			{
				path: 'managementSlot',
				loadChildren: "./pages/aside-left-display-disabled/management-slot/management-slot.module#ManagementSlotModule"
			},
			{
				path: 'reportOwner',
				loadChildren: "./pages/aside-left-display-disabled/report-owner/report-owner.module#ReportOwnerModule"
			},
			{
				path: 'reportNearOwner',
				loadChildren: "./pages/aside-left-display-disabled/report-near/report-near.module#ReportNearModule"
			},
			{
				path: 'signup',
				loadChildren: "./pages/aside-left-display-disabled/signup/signup.module#SignupModule"
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