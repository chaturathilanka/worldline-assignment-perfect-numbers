package com.worldline.assignment.controller;

import com.worldline.assignment.dto.ResponseDTO;
import com.worldline.assignment.util.OperationsUtil;
import com.worldline.assignment.service.NumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfectnumbers/v1")
public class NumberController {

    @Autowired
    private NumberService numberService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberController.class);

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    /**
     * controller - check the given number is a perfect number
     * @param number
     * @param correlationId
     * @return
     */
    @GetMapping(path = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> checkIsPerfectNumber(
            @PathVariable(name = "number", required = true) Integer number,
            @RequestAttribute(name = OperationsUtil.CORRELATION_ID) String correlationId) {
        LOGGER.info("request processing : check perfect number : " + number);
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<Integer> opInputNumber = Optional.ofNullable(number);
        if (!opInputNumber.isPresent()) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Input number required");
            LOGGER.error("missing input number");
            return ResponseEntity.badRequest().body(responseDTO);
        }

        try {
            int inputNumber = opInputNumber.get();
            boolean isPerfectNumber = this.numberService.isPerfectNumber(number,
                    correlationId);
            if (!isPerfectNumber) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("given number : " + inputNumber + " is not a perfect number");
                LOGGER.info("isPerfectNumber() : output : " + false);
                return ResponseEntity.ok(responseDTO);
            } else if (isPerfectNumber) {
                responseDTO.setSuccess(true);
                responseDTO.setMessage(inputNumber +" is a perfect number");
                LOGGER.info("isPerfectNumber() : input : " + inputNumber + " output : " + true );
                return ResponseEntity.ok(responseDTO);
            }
        } catch (Exception ex) {
            LOGGER.error("an exception occurred", ex);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("An error occurred while processing the request");
        }
        return ResponseEntity.internalServerError().body(responseDTO);
    }

    /**
     * controller - get the perfect number series in given range
     * @param start
     * @param end
     * @param correlationId
     * @return
     */
    @GetMapping(path = "/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getPerfectNumberSeries(
            @PathVariable(name = "start", required = true) Integer start,
            @PathVariable(name = "end", required = true) Integer end,
            @RequestAttribute(name = OperationsUtil.CORRELATION_ID) String correlationId) {
        LOGGER.info("request processing : get perfect number series in range");
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<Integer> opStartNumber = Optional.ofNullable(start);
        Optional<Integer> opEndNumber = Optional.ofNullable(end);

        if (!opStartNumber.isPresent() || !opStartNumber.isPresent()) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Input numbers required");
            LOGGER.error("missing input numbers");
            return ResponseEntity.badRequest().body(responseDTO);
        }

        try {
            int startNumber = opStartNumber.get();
            int endNumber = opEndNumber.get();
            List<Integer> numberSeries = this.numberService.getPerfectNumberSeries(startNumber, endNumber,
                    correlationId);
            if (numberSeries.isEmpty()) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("No perfect numbers in given range : " + startNumber + " - " + endNumber);
                LOGGER.info("getPerfectNumberSeries() : output : empty");
                return ResponseEntity.ok(responseDTO);
            } else if (!numberSeries.isEmpty()) {
                responseDTO.setSuccess(true);
                responseDTO.setMessage("perfect numbers found");
                responseDTO.setNumbers(numberSeries);
                LOGGER.info("isPerfectNumber() : output : " + numberSeries);
                return ResponseEntity.ok(responseDTO);
            }
        } catch (Exception ex) {
            LOGGER.error("an exception occurred", ex);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("An error occurred while processing the request");
        }
        return ResponseEntity.internalServerError().body(responseDTO);
    }


}
