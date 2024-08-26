import { Routes } from '@angular/router';
import {RestComponent} from "./rest/rest.component";
import {PathDoesNotExistsComponent} from "./pathdoesnotexists/path.does.not.exists.component";
import {AppComponent} from "./app.component";
import {MainPageComponent} from "./main-page/main-page.component";
import {AccountComponent} from "./account/account.component";

export const routes: Routes = [
  {path: 'rest', component: RestComponent},
  {path: 'main-page', component: MainPageComponent},
  {path: 'account', component: AccountComponent},
  {path: '', redirectTo:"/main-page", pathMatch: 'full'},
  {path: '**' , component: PathDoesNotExistsComponent, pathMatch:'full'}
];
