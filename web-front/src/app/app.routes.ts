import {Routes} from '@angular/router';
import {RestComponent} from "./rest/rest.component";
import {PathDoesNotExistsComponent} from "./pathdoesnotexists/path.does.not.exists.component";
import {MainPageComponent} from "./main-page/main-page.component";
import {AccountComponent} from "./account/account.component";
import {HelpPageComponent} from "./help-page/help-page.component";
import {FaqPageComponent} from "./faq-page/faq-page.component";
import {ContactUsComponent} from "./contact-us/contact-us.component";

export const routes: Routes = [
  {path: 'rest', title: 'restComponent', component: RestComponent},
  {path: 'main-page', title: 'mainPage', component: MainPageComponent},
  {path: 'account', title: 'account', component: AccountComponent},
  {path: 'help', title: 'helpPage', component: HelpPageComponent, children:
  [
    {path: 'faq', title: 'faqPage' , component: FaqPageComponent},
    {path: 'contact-us', title: 'contactUsPage', component: ContactUsComponent}
  ]},
  {path: '', redirectTo: "/main-page", pathMatch: 'full'},
  {path: '**', component: PathDoesNotExistsComponent, pathMatch: 'full'},
];


