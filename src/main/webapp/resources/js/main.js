function save(args, dialog) {
    if (args.validationFailed) {
        jQuery('#' + dialog).effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}
