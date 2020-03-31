import { Component, OnInit, Input } from '@angular/core';
import { Horse } from 'src/app/dto/horse';

@Component({
  selector: 'app-horse-list',
  templateUrl: './horse-list.component.html',
  styleUrls: ['./horse-list.component.scss']
})
export class HorseListComponent implements OnInit {
  @Input() horse: Horse;

  constructor() { }

  ngOnInit(): void {
  }

}
