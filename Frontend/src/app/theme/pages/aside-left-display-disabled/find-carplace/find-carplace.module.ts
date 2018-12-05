import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { FindCarplaceComponent } from './find-carplace.component';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { AgmCoreModule } from '@agm/core';
import { GoogleMapService } from '../../../../_services/google-map.service';
import { DlDateTimePickerDateModule } from 'angular-bootstrap-datetimepicker';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        "children": [
            {
                "path": "",
                "component": FindCarplaceComponent
            }
        ]
    }
];
@NgModule({
    imports: [
        CommonModule, RouterModule.forChild(routes), LayoutModule,
        AgmCoreModule,DlDateTimePickerDateModule
    ],
    exports: [
        RouterModule
    ],
    declarations: [
        FindCarplaceComponent
    ],
    providers: [
        GoogleMapService
    ]
})
export class FindCarplaceModule {

}