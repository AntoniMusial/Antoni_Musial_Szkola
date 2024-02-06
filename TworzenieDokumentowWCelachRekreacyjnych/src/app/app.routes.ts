import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LegitkaComponent } from './legitka/legitka.component';
import { AppComponent } from './app.component';

export const routes: Routes = [
    {path: "home", component: HomeComponent},
    {path: "legitka", component: LegitkaComponent},
    {path: "", redirectTo: '/home', pathMatch: 'full'}
];
