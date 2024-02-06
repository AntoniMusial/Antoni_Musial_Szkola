import { Component } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { LegitkaComponent } from './legitka/legitka.component';
import { RouterOutlet, RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeComponent, LegitkaComponent, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Tworzenie Dokumentow W Celach Rekreacyjnych';
  autor = 'Antoni Musiał';
}
