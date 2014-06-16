# FIT ACM QR Code Registration
Registration system using webcam. This is a paperless registration system if everyone in the audience has a smartphone to display their QR/Barcode/Digital Matrix/etc for their registration.

## What Is This

Normally, registration takes time in an event because everyone would have to write down their names. Using this project, the audience just flashes his/her ticket code to the webcam and he/she is registered. 

This system scans for a QR/Barcode/Digital Matrix/etc using the webcam for registration. Registration is done by matching the scanned input to a name in the database. The [Webcam Capture](https://github.com/sarxos/webcam-capture) project for the utilization of the webcam and the [ZXing](https://github.com/zxing/zxing) library for decoding QR/Barcode/Digital Matrix/etc were used in this project.

## Steps for pre-registering (audience)
1. Go to https://<event_url>.eventbrite.com
2. Click Register
3. Enter your valid details
4. Click Complete Registration
5. Download or print the pdf ticket sent to the email you supplied
6. Bring your ticket on the event day
7. Show your ticket to the registration laptop's webcam

## Steps for registering (organizer)
1. Create an event at https://eventbrite.com and publicize the url
2. When pre-registration is finished, get the names. On eventbrite.com, click "My Events"
3. On your event's row, click "Manage"
4. On the "Sales by Ticket Type" section below, click "View Sales"
5. Click "File Type" on the "Export" section
6. Click "Export to CSV" to save and download the data
7. Put the CSV file on the project folder (beside QR_lib, src, target) and rename it as "file.csv" (required)
8. Run the project using eclipse

Project was used in the registration of the Software Freedom Day Philippines 2013 at the FEU Auditorium, organized by the Computer Professionals' Union. The project registered 350+ people in 25 minutes. 

## License

Copyright (C) 2014 Edwin Richbald Salinas, FEU Institute of Technology Association for Computing Machinery Student Chapter

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
