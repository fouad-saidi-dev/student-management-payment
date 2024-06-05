import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {PaymentType} from "../model/students.model";
import {StudentsService} from "../services/students.service";

@Component({
  selector: 'app-new-payment',
  templateUrl: './new-payment.component.html',
  styleUrl: './new-payment.component.css'
})
export class NewPaymentComponent implements OnInit {
  paymentFormGroup!: FormGroup;
  studentCode!: string;
  paymentType: string[] = [];
  pdfFileUrl!: string;
  showProgress: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private studentsService: StudentsService) {
  }

  ngOnInit(): void {
    /*for (let elt in this.paymentType) {
      let value: string = PaymentType[elt]
      if (value === 'string') {
        this.paymentType.push(value)
      }
    }*/
    this.paymentType = Object.keys(PaymentType).filter(key => isNaN(Number(key)));

    this.studentCode = this.activatedRoute.snapshot.params['studentCode']
    this.paymentFormGroup = this.formBuilder.group({
      date: this.formBuilder.control(''),
      amount: this.formBuilder.control(''),
      type: this.formBuilder.control(''),
      studentCode: this.formBuilder.control(this.studentCode),
      fileSource: this.formBuilder.control(''),
      fileName: this.formBuilder.control(''),
    })
  }

  selectFile(event: any) {
    if (event.target.files.length > 0) {
      let file = event.target.files[0]
      this.paymentFormGroup.patchValue({
        fileSource: file,
        fileName: file.name
      });
      this.pdfFileUrl = window.URL.createObjectURL(file);
    }
  }

  savePayment() {
    let date: Date = new Date(this.paymentFormGroup.value.date);
    let formattedDate: string = date.getDate() + "/" + (date.getMonth() + 1) + '/' + date.getFullYear();
    this.showProgress = true;
    const formData: FormData = new FormData();
    console.log(formattedDate)
    formData.append('file', this.paymentFormGroup.get('fileSource')!.value);
    formData.append('amount', this.paymentFormGroup.value.amount)
    formData.append('type', this.paymentFormGroup.value.type)
    formData.append('date', formattedDate)
    formData.append('studentCode', this.paymentFormGroup.value.studentCode)

    this.studentsService.savePayment(formData).subscribe({
      next: value => {
        this.showProgress = false;
        alert('Payment Saved successfully')
      },
      error: err => {
        console.log(err)
      }
    })

  }

  afterLoadComplete(event: any) {
    console.log(event)
  }
}
