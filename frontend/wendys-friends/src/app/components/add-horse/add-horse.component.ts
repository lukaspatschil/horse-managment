import { Component, OnInit, EventEmitter, Output, Input } from "@angular/core";
import { OwnerService } from "../../service/owner.service";

import { Owner } from "src/app/dto/owner";
import { Horse } from 'src/app/dto/horse';

@Component({
  selector: "app-add-horse",
  templateUrl: "./add-horse.component.html",
  styleUrls: ["./add-horse.component.scss"]
})
export class AddHorseComponent implements OnInit {
  @Input() owners: Owner[];
  @Output() addHorse: EventEmitter<any> = new EventEmitter();

  name: string;
  notes: string;
  rating: number;
  birthday: string;
  owner: number;
  race: string;
  image: string;
  type: string;
  imageFile: File;
  imageEncode: string;
  fileList: FileList;

  constructor(private ownerService: OwnerService) {}

  ngOnInit(): void {
    this.rating = 0;
    this.owner = 0;
    this.race = "0";
  }

  onSubmit() {
    if (this.notes == null) {
      this.notes = "";
    }
    const horse = new Horse(0, this.name, this.notes, this.rating, this.birthday, this.owner, this.race, this.imageEncode, this.type, null, null);

    console.log(horse);

    this.addHorse.emit(horse);
  }

  picked(event: any) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      this.imageFile = file;
      this.handleInputChange(file); // turn into base64
    } else {
      alert("No file selected");
    }
  }

  handleInputChange(files: any) {
    const file = files;
    const reader = new FileReader();

    reader.onloadend = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e: any) {
    const reader = e.target;
    const base64result = reader.result.substr(reader.result);

    this.image = base64result;
    this.type = base64result.substr(0, base64result.indexOf(",") + 1);
    this.imageEncode = base64result.substr(base64result.indexOf(",") + 1);
  }
}
