import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-new-payment',
  templateUrl: './new-payment.component.html',
  styleUrl: './new-payment.component.css'
})
export class NewPaymentComponent implements OnInit{
  paymentFormGroup!:FormGroup;
  constructor(private formBuilder:FormBuilder) {
  }
  ngOnInit(): void {
    this.paymentFormGroup = this.formBuilder.group({
      date:this.formBuilder.control(''),
      amount:this.formBuilder.control(''),
      type:this.formBuilder.control(''),
      studentCode:this.formBuilder.control(''),
    })
  }

}
