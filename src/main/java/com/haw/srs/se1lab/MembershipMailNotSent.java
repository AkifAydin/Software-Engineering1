package com.haw.srs.se1lab;

import lombok.Value;

@Value
class MembershipMailNotSent extends Exception {

    MembershipMailNotSent(String recipient) {
        super(String.format("Could not send membership mail to %s.", recipient));

    }
}