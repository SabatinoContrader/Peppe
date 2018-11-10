import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StopsInProgressComponent } from './stops-in-progress.component';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { Routes, RouterModule } from '@angular/router';
import { LayoutModule } from '../../../layouts/layout.module';
import { NgForm, FormsModule } from '@angular/forms';
import { ExtensionStopsRowComponent } from '../extension-stops-row/extension-stops-row.component';
import { StopsInProgressRowComponent } from '../stops-in-progress-row/stops-in-progress-row.component';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        "children": [
            {
                "path": "",
                "component": StopsInProgressComponent
            }
        ]
    }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        LayoutModule,
        FormsModule
    ],
    exports: [
        RouterModule
    ],
    declarations: [StopsInProgressComponent,
        StopsInProgressRowComponent]
})
export class StopsInProgressModule { }
