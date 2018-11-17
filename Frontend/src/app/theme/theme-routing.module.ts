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
                path: 'parksMap/:car/:address',
                loadChildren: "./pages/aside-left-display-disabled/parksMap/parksMap.module#ParksMapModule"
            },
            {
                path: 'stopsInProgress',
                loadChildren: "./pages/aside-left-display-disabled/stops-in-progress/stops-in-progress.module#StopsInProgressModule"
            },
            {
                path: 'stopsHistory',
                loadChildren: "./pages/aside-left-display-disabled/stops-history/stops-history.module#StopsHistoryModule"
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
                path: 'paymentsHistory',
                loadChildren: "./pages/aside-left-display-disabled/payment/payment.module#PaymentModule"
            },
            {
                path: 'balance',
                loadChildren: "./pages/aside-left-display-disabled/balance/balance.module#BalanceModule"
            },
            {
                path: 'payments',
                loadChildren: "./pages/aside-left-display-disabled/payments/payments.module#PaymentsModule"
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
            //private owner
            {
                path: 'nearbyBooking',
                loadChildren: "./pages/aside-left-display-disabled/nearby-booking/nearby-booking.module#NearbyBookingModule"
            },
            {
                path: 'farBooking',
                loadChildren: "./pages/aside-left-display-disabled/far-booking/far-booking.module#FarBookingModule"
            },
            {
                path: 'bookingHistory',
                loadChildren: "./pages/aside-left-display-disabled/booking-history/booking-history.module#BookingHistoryModule"
            },

            //all owners
            {
                path: 'reportPlatform',
                loadChildren: "./pages/aside-left-display-disabled/report-platform/report-platform.module#ReportPlatformModule"
            },
            {
                path: 'dashboard',
                loadChildren: "./pages/aside-left-display-disabled/dashboard/dashboard.module#DashboardModule"
            },
            {
                path: 'illegalStops',
                loadChildren: "./pages/aside-left-display-disabled/illegal-stops/illegal-stops.module#IllegalStopsModule"
            },
            {
                path: 'disabledReporting',
                loadChildren: "./pages/aside-left-display-disabled/disabled-reporting/disabled-reporting.module#DisabledReportingModule"
            },
            {
                path: 'disservices',
                loadChildren: "./pages/aside-left-display-disabled/disservices/disservices.module#DisservicesModule"
            },
            {
                path: 'infoManagePark',
                loadChildren: "./pages/aside-left-display-disabled/info-manage-park/info-manage-park.module#InfoManageParkModule"
            },
            {
                path: 'parkingPrice',
                loadChildren: "./pages/aside-left-display-disabled/parking-price/parking-price.module#ParkingPriceModule"
            },
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