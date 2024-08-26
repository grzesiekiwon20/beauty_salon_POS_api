import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-path-does-not-exists',
  standalone: true,
  imports: [
    RouterOutlet
  ],
  templateUrl: './path.does.not.exists.component.html',
  styleUrl: './path.does.not.exists.component.css'
})
export class PathDoesNotExistsComponent {

}
