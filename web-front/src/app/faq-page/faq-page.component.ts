import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-faq-page',
  standalone: true,
  imports: [
    RouterOutlet
  ],
  templateUrl: './faq-page.component.html',
  styleUrl: './faq-page.component.css'
})
export class FaqPageComponent {

}
