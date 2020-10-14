/** Prototipo para convertir string a hexadecimal */
String.prototype.hexEncode = function () {
    var hex, i;
    var result = "";
    for (i = 0; i < this.length; i++) {
        hex = this.charCodeAt(i).toString(16);
        result += hex;
    }
    return result
}

/** Prototipo para convertir Array a Objeto */
Array.prototype.toObject = function () {
    var result = {};
    this.forEach((item) => {
        if (result[item.name] == null || result[item.name] == undefined)
            result[item.name] = item.value;
    });
    return result;
}

/** Prototipo para convertir datestring a urldatestring*/
String.prototype.toDateStringUrl = function () {
    if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(this.valueOf()))
        return '';

    // convertir los numeros a enteros
    var parts = this.valueOf().split("/");
    var day = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[2], 10);

    var result = `${month}/${day}/${year}`;
    return result
}

/** Prototipo para convertir un texto a una fecha válida */
String.prototype.toDate = function () {
    var dateTimePart = this.valueOf().split(' ');

    if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateTimePart[0]))
        return new Date(0, 0, 0);

    var datePart = dateTimePart[0].split("/");
    var day = parseInt(datePart[0], 10);
    var month = parseInt(datePart[1], 10);
    var year = parseInt(datePart[2], 10);
    var hour = 0;
    var minute = 0;

    if (dateTimePart.length > 1) {
        hour = parseInt(dateTimePart[1].split(":")[0], 10);
        minute = parseInt(dateTimePart[1].split(":")[1], 10);
    }

    var result = new Date(year, month - 1, day, hour, minute);
    return result;
}

/** Prototipo para devolver una fecha sin la parte del tiempo */
Date.prototype.withoutTime = function () {
    var d = new Date(this);
    d.setHours(0, 0, 0, 0);
    return d;
}

/// Extension de jquery validator para validar fechas correctamente.
$(function () {
    $.validator.methods.date = function (value, element) {
        return this.optional(element) || isValidDate(value);
    }
});