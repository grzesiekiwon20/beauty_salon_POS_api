import { Component } from '@angular/core';
import {RouterLink, RouterModule, RouterOutlet} from "@angular/router";


@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterModule
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {

}
