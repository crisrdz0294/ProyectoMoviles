<?php
include ("../data/dbBusiness.php");
include ("../domain/Business.php");
include ("../data/dtConexion.php");

class ctrBusiness {

    function ctrBusiness(){
    }

    function getAllBisness(){
        $dbBusiness = new dbBusiness();
        
        if($Business = $dbBusiness->getBusiness()){
            echo json_encode($Business);
        }else {
            echo json_encode(
            array(
                "status" => "query"
            ));
        }
    }

    function newBusiness(){
        $dbBusiness = new dbBusiness();
        $data = json_decode(file_get_contents("php://input"));
        $business = new Business();

        $business->setIdLegalCertificate($data->idLegalCertificate);
        $business->setName($data->name);
        $business->setTelephone($data->telephone);
        $business->setLocation($data->location);
        $business->setEmail($data->email);
        $business->setContact($data->contact);

        if($dbBusiness->insert($business)){
            echo true;
        }else {
            echo false;
        }
    }

    function updateBusiness(){
        $dbBusiness = new dbBusiness();
        $data = json_decode(file_get_contents("php://input"));
        $business = new Business();

        $business->setIdLegalCertificate($data->idLegalCertificate);
        $business->setName($data->name);
        $business->setTelephone($data->telephone);
        $business->setLocation($data->location);
        $business->setEmail($data->email);
        $business->setContact($data->contact);

        if($dbBusiness->update($business)){
            echo true;
        }else {
            echo false;
        }
    }

    function aBusiness(){
        $dbBusiness = new dbBusiness();
        $data = json_decode(file_get_contents("php://input"));
        $id = $data->id;
        if($Business = $dbBusiness->getABusiness($id)){
            echo json_encode($Business);
        }else {
            echo false;
        }
    }

    function deleteBusiness(){
        $dbBusiness = new dbBusiness();
        $data = json_decode(file_get_contents("php://input"));
        $id = $data->id;
        if($dbBusiness->delete($id)){
            echo json_encode(
            array(
                "status" => "true"
            ));
        }else {
            echo json_encode(
            array(
                "status" => "query"
            ));
        }
    }


}
$data = json_decode(file_get_contents("php://input"));
$op = $data->op;


$control = new ctrBusiness;

if($op=="getAllBisness"){
    $control->getAllBisness();
}
if($op=="newBusiness"){
    $control->newBusiness();
}
if($op=="aBusiness"){
    $control->aBusiness();
}

if($op=="updateBusiness"){
    $control->updateBusiness();
}

if($op=="deleteBusiness"){
    $control->deleteBusiness();
}








?>
