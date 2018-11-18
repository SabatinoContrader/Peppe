import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { NgForm, FormsModule } from '@angular/forms';
import { StopsHistoryComponent } from './stops-history.component';
import { StopsInProgressRowComponent } from '../stops-in-progress-row/stops-in-progress-row.component';
import { StopsHistoryRowComponent } from '../stops-history-row/stops-history-row.component';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        "children": [
            {
                "path": "",
                "component": StopsHistoryComponent
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

    declarations: [StopsHistoryComponent,
        StopsHistoryRowComponent]

})
export class StopsHistoryModule { }
