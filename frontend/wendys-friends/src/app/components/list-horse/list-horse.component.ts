import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { HorseService } from '../../service/horse.service';

import { Horse } from 'src/app/dto/horse';

@Component({
  selector: 'app-list-horse',
  templateUrl: './list-horse.component.html',
  styleUrls: ['./list-horse.component.scss']
})
export class ListHorseComponent implements OnInit {
  @Input() horse: Horse;
  @Output() deleteHorse: EventEmitter<Horse> = new EventEmitter();

  constructor(private horseService:HorseService) { }

  ngOnInit(): void {
  }

  onDelete(horse) {
    this.deleteHorse.emit(horse);
  }

}
