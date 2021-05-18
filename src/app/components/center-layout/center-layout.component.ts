import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { InputTextService } from '../../services/input-text.service';

@Component({
  selector: 'app-center-layout',
  templateUrl: './center-layout.component.html',
  styleUrls: ['./center-layout.component.scss'],
  providers: [InputTextService]
})
export class CenterLayoutComponent implements OnInit {

  textModel: string = "";

  constructor(private inputtextService: InputTextService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.inputtextService.sendText(this.textModel).subscribe(
      response => console.log(response),
      err => console.log(err)
    );
  }

  togglePause() {
    this.inputtextService.togglePause().subscribe(
      response => console.log(response),
      err => console.log(err)
    );
  }

}
